package org.mayocat.accounts.url;

import java.net.MalformedURLException;
import java.net.URL;

import org.mayocat.accounts.model.Tenant;
import org.mayocat.url.AbstractEntityURLFactory;
import org.mayocat.url.EntityURLFactory;
import org.mayocat.url.URLType;
import org.xwiki.component.annotation.Component;

/**
 * @version $Id$
 */
@Component
public class TenantURLFactory extends AbstractEntityURLFactory<Tenant> implements EntityURLFactory<Tenant>
{
    public URL create(Tenant entity, Tenant tenant)
    {
        return this.create(entity, tenant, URLType.PUBLIC);
    }

    @Override
    public URL create(Tenant entity, Tenant tenant, URLType type)
    {
        try {
            switch (type) {
                case API:
                    return new URL(getSchemeAndDomain(tenant) + "/api/tenant/");
                case PUBLIC:
                default:
                    return new URL(getSchemeAndDomain(tenant));
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
