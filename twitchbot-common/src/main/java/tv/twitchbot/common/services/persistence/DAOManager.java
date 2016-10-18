package tv.twitchbot.common.services.persistence;

import com.datastax.driver.mapping.MappingManager;
import tv.twitchbot.common.dao.*;

/**
 * Created by cobi on 10/16/2016.
 */
public class DAOManager {
    private DAOTenant daoTenant;
    private DAOGlobalUser daoGlobalUser;
    private DAOTenantUserPermissions daoTenantUserPermissions;
    private DAOKeyValueEntry daoKeyValueEntry;
    private DAOTenantConfiguration daoTenantConfiguration;
    private DAOGlobalConfiguration daoGlobalConfiguration;

    public DAOManager(MappingManager mappingManager) {
        daoTenant = new DAOTenant(mappingManager);
        daoGlobalUser = new DAOGlobalUser(mappingManager);
        daoTenantUserPermissions = new DAOTenantUserPermissions(mappingManager);
        daoKeyValueEntry = new DAOKeyValueEntry(mappingManager);
        daoTenantConfiguration = new DAOTenantConfiguration(mappingManager);
        daoGlobalConfiguration = new DAOGlobalConfiguration(mappingManager);
    }

    public DAOTenant getDaoTenant() {
        return daoTenant;
    }

    public DAOGlobalUser getDaoGlobalUser() {
        return daoGlobalUser;
    }

    public DAOTenantUserPermissions getDaoTenantUserPermissions() {
        return daoTenantUserPermissions;
    }

    public DAOKeyValueEntry getDaoKeyValueEntry() {
        return daoKeyValueEntry;
    }

    public DAOTenantConfiguration getDaoTenantConfiguration() {
        return daoTenantConfiguration;
    }

    public DAOGlobalConfiguration getDaoGlobalConfiguration() {
        return daoGlobalConfiguration;
    }
}
