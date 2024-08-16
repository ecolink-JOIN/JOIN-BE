package com.join.core.file.factory;

import com.join.core.file.domain.ImageFile;
import com.join.core.file.domain.SinglePhoto;

@FunctionalInterface
public interface SinglePhotoFactory<P extends SinglePhoto> {

	P convert(ImageFile file);

}
