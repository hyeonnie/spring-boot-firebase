package com.example.demo.core;

public abstract class AbstractFirebaseCoreService<T> {

	private final AbstractFirebaseCoreRepository<T> abstractFirebaseCoreRepository;
	
	private final Class<T> clazz;
	
	private final String collection;
	
	public AbstractFirebaseCoreService(final AbstractFirebaseCoreRepository<T> abstractFirebaseCoreRepository, final Class<T> clazz) {
		this.abstractFirebaseCoreRepository = abstractFirebaseCoreRepository;
		this.clazz = clazz;
		this.collection = clazz.getSimpleName().toLowerCase();
	}
	
	public boolean create(final T field, final String document) {
		return abstractFirebaseCoreRepository.create(field, document);
	}
	
	public boolean update(final T field, final String document) {
		return abstractFirebaseCoreRepository.update(field, document);
	}
	
	public T select(final String document) {
		return abstractFirebaseCoreRepository.select(clazz, collection, document);
	}
	
	public boolean delete(final String document) {
		return abstractFirebaseCoreRepository.delete(collection, document);
	}
	
}
