package org.mayocat.shop.front;

import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import org.xwiki.component.annotation.Role;

/**
 * @version $Id$
 */
@Role
public interface FrontContextManager
{

    Map<String, Object> getContext(UriInfo uriInfo);
}
