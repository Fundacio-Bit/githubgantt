package org.fundaciobit.githubgantt.back.preparer;


import java.util.Map;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.fundaciobit.githubgantt.commons.utils.Version;
import org.springframework.stereotype.Component;


/**
 * @author GenApp
 * @author anadal
 *
 */
@RunAs("GHG_USER")
@Component
public class PeuPreparer implements ViewPreparer {

	protected final Logger log = Logger.getLogger(getClass());

	protected Version versio = new Version();

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {
		Map<String, Object> request = tilesRequest.getContext("request");
		request.put("versio", versio);
	}
}
