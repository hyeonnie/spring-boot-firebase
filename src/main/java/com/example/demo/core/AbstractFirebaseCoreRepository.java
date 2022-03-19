package com.example.demo.core;

import org.springframework.util.ObjectUtils;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractFirebaseCoreRepository<T> {
	
	public Firestore getFirestore() {
		return FirestoreClient.getFirestore();
	}
	
	public CollectionReference getCollectionReference(final String collection) {
		return getFirestore()
				.collection(collection);
	}
	
	public DocumentReference getDocumentReference(final String collection, String document) {
		return getCollectionReference(collection)
				.document(document);
	}
	
	public ApiFuture<DocumentSnapshot> getApiFutureDocumentSnapshot(final String collection, final String document) {
		return getDocumentReference(collection, document)
				.get();
	}
	
	public DocumentSnapshot getDocumentSnapshot(final String collection, final String document) {
		DocumentSnapshot documentSnapshot = null;
		try {
			documentSnapshot = getApiFutureDocumentSnapshot(collection, document).get();
		} catch (final Exception e) {
			log.error("getDocumentSnapshot, Exception: {}", e.toString());
		}
		return documentSnapshot;
	}
	
	public boolean create(final T field, final String document) {
		Timestamp createTime = null;
		try {
			final String collection = field.getClass().getSimpleName().toLowerCase();
			createTime = getDocumentReference(collection, document)
					.set(field)
					.get()
					.getUpdateTime();
		} catch (final Exception e) {
			log.error("create, Exception: {}", e.toString());
		}
		return !ObjectUtils.isEmpty(createTime);
	}
	
	public boolean update(final T field, final String document) {
		Timestamp updateTime = null;
		try {
			final String collection = field.getClass().getSimpleName().toLowerCase();
			updateTime = getDocumentReference(collection, document)
					.set(field)
					.get()
					.getUpdateTime();
		} catch (final Exception e) {
			log.error("update, Exception: {}", e.toString());
		}
		return !ObjectUtils.isEmpty(updateTime);
	}
	
	public T select(final Class<T> clazz, final String collection, final String document) {
		DocumentSnapshot documentSnapshot = getDocumentSnapshot(collection, document);
		return documentSnapshot.exists()
				? documentSnapshot.toObject(clazz)
				: null;
	}
	
	public boolean delete(final String collection, final String document) {
		Timestamp deleteTime = null;
		try {
			deleteTime = getDocumentReference(collection, document)
					.delete()
					.get()
					.getUpdateTime();
		} catch (final Exception e) {
			log.error("delete, Exception: {}", e.toString());
		}
		return !ObjectUtils.isEmpty(deleteTime);
	}
	
}
