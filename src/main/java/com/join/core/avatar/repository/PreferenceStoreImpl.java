package com.join.core.avatar.repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.join.core.avatar.domain.Avatar;
import com.join.core.avatar.domain.Preference;
import com.join.core.avatar.dto.ChangePreferenceRequest;
import com.join.core.avatar.service.PreferenceStore;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Transactional
@Component
public class PreferenceStoreImpl implements PreferenceStore {

	private final DynamoDbTable<Preference> preferenceTable;

	public PreferenceStoreImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
		this.preferenceTable = dynamoDbEnhancedClient.table("Preferences",
			TableSchema.fromBean(Preference.class));
	}

	@Override
	public void changePreferenceOf(Avatar avatar, ChangePreferenceRequest request) {
		Preference preference = request.toObject(avatar.getAvatarToken());
		preferenceTable.putItem(preference);
	}

}
