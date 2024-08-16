package com.join.core.file.factory;

import com.join.core.file.domain.ImageFile;
import com.join.core.file.domain.MultiPhoto;
import com.join.core.file.domain.MultiPhotoContainer;

@FunctionalInterface
public interface MultiPhotoFactory<T extends MultiPhotoContainer<P>, P extends MultiPhoto> {

	P convert(ImageFile file, int order, T container);

}
