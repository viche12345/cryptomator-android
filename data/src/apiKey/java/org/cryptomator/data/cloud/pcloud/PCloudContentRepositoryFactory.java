package org.cryptomator.data.cloud.pcloud;

import static org.cryptomator.domain.CloudType.PCLOUD;

import android.content.Context;

import org.cryptomator.data.repository.CloudContentRepositoryFactory;
import org.cryptomator.domain.Cloud;
import org.cryptomator.domain.PCloud;
import org.cryptomator.domain.repository.CloudContentRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PCloudContentRepositoryFactory implements CloudContentRepositoryFactory {

	private final Context context;

	@Inject
	public PCloudContentRepositoryFactory(Context context) {
		this.context = context;
	}

	@Override
	public boolean supports(Cloud cloud) {
		return cloud.type() == PCLOUD;
	}

	@Override
	public CloudContentRepository<PCloud, PCloudNode, PCloudFolder, PCloudFile> cloudContentRepositoryFor(Cloud cloud) {
		PCloud pCloud = (PCloud) cloud;
		return new PCloudContentRepository(pCloud, PCloudClient.Companion.createClient(pCloud, context), context);
	}

}
