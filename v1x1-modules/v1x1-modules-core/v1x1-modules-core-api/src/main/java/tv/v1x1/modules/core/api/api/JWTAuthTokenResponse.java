package tv.v1x1.modules.core.api.api;

/**
 * Created by cobi on 12/17/2016.
 */
public class JWTAuthTokenResponse extends AuthTokenResponse {
    public JWTAuthTokenResponse() {
    }

    public JWTAuthTokenResponse(final String authorization, final long expires) {
        super(authorization, expires);
    }
}
