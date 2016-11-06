package tv.v1x1.common.rpc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.v1x1.common.dto.messages.Message;
import tv.v1x1.common.dto.messages.Request;
import tv.v1x1.common.dto.messages.Response;
import tv.v1x1.common.dto.messages.responses.ExceptionResponse;
import tv.v1x1.common.modules.GlobalConfiguration;
import tv.v1x1.common.modules.Module;
import tv.v1x1.common.modules.ModuleSettings;
import tv.v1x1.common.modules.TenantConfiguration;
import tv.v1x1.common.services.queue.MessageQueue;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cobi on 10/8/2016.
 */
public abstract class Service<T extends Request, U extends Response<T>> implements Comparable<Service<T, U>> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final Module<?, ?, ?, ?> module;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final String serviceName;
    private final Class<T> requestClass;

    public Service(final Module<?, ?, ?, ?> module, final String serviceName, final Class<T> requestClass) {
        this.module = module;
        this.serviceName = serviceName;
        this.requestClass = requestClass;
    }

    public void start() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                final MessageQueue messageQueue = module.getMessageQueueManager().forName(getServiceQueue());
                for(;;) {
                    try {
                        final Message m = messageQueue.get();
                        if(!requestClass.isInstance(m)) {
                            LOG.warn("Invalid class seen on request queue: {} expected: {}", m.getClass().getCanonicalName(), requestClass.getCanonicalName());
                            continue;
                        }
                        @SuppressWarnings("unchecked") final T request = (T) m;
                        handleRequest(request);
                    } catch (final InterruptedException e) {
                        break;
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected void handleRequest(final T request) {
        try {
            module.send(request.getResponseQueueName(), call(request));
        } catch(final Exception e) {
            LOG.warn("Got exception while responding to request.", e);
            module.send(request.getResponseQueueName(), new ExceptionResponse(module.toDto(), request.getMessageId(), e));
            throw e;
        }
    }

    protected Module<?, ?, ?, ?> getModule() {
        return module;
    }

    public void shutdown() {
        executorService.shutdownNow();
    }

    protected abstract U call(T request);

    private String getServiceQueue() {
        return "Service|" + serviceName;
    }

    @Override
    public int compareTo(final Service<T, U> o) {
        return serviceName.compareTo(o.serviceName);
    }
}
