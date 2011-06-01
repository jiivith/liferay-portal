/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.cache.ehcache;

import java.beans.PropertyChangeListener;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Statistics;
import net.sf.ehcache.Status;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.event.RegisteredEventListeners;
import net.sf.ehcache.exceptionhandler.CacheExceptionHandler;
import net.sf.ehcache.extension.CacheExtension;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.statistics.CacheUsageListener;
import net.sf.ehcache.statistics.LiveCacheStatistics;
import net.sf.ehcache.statistics.sampled.SampledCacheStatistics;
import net.sf.ehcache.transaction.manager.TransactionManagerLookup;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterManager;

/**
 * @author Edward Han
 */
public class ModifiableEhcacheWrapper implements Ehcache {
	public ModifiableEhcacheWrapper(Ehcache ehcache) {
		_ehcache = ehcache;
	}

	public void addPropertyChangeListener(
		PropertyChangeListener propertyChangeListener) {

		_ehcache.addPropertyChangeListener(propertyChangeListener);
	}

	public void addReference() {
		_referenceCounter.incrementAndGet();
	}

	public void bootstrap() {
		_ehcache.bootstrap();
	}

	public long calculateInMemorySize()
		throws IllegalStateException, CacheException {

		return _ehcache.calculateInMemorySize();
	}

	public void clearStatistics() {
		_ehcache.clearStatistics();
	}

	public Object clone() throws CloneNotSupportedException {
		return _ehcache.clone();
	}

	public void disableDynamicFeatures() {
		_ehcache.disableDynamicFeatures();
	}

	public void dispose() throws IllegalStateException {
		_ehcache.dispose();
	}

	public boolean equals(Object obj) {
		return _ehcache.equals(obj);
	}

	public void evictExpiredElements() {
		_ehcache.evictExpiredElements();
	}

	public void flush() throws IllegalStateException, CacheException {
		_ehcache.flush();
	}

	public Element get(Object key) throws IllegalStateException, CacheException {
		return _ehcache.get(key);
	}

	public Element get(Serializable key)
		throws IllegalStateException, CacheException {

		return _ehcache.get(key);
	}

	public int getActiveReferenceCount() {
		return _referenceCounter.get();
	}

	public Map getAllWithLoader(Collection collection, Object o)
		throws CacheException {

		return _ehcache.getAllWithLoader(collection, o);
	}

	public float getAverageGetTime() {
		return _ehcache.getAverageGetTime();
	}

	public BootstrapCacheLoader getBootstrapCacheLoader() {
		return _ehcache.getBootstrapCacheLoader();
	}

	public CacheConfiguration getCacheConfiguration() {
		return _ehcache.getCacheConfiguration();
	}

	public RegisteredEventListeners getCacheEventNotificationService() {
		return _ehcache.getCacheEventNotificationService();
	}

	public CacheExceptionHandler getCacheExceptionHandler() {
		return _ehcache.getCacheExceptionHandler();
	}

	public CacheManager getCacheManager() {
		return _ehcache.getCacheManager();
	}

	public int getDiskStoreSize() throws IllegalStateException {
		return _ehcache.getDiskStoreSize();
	}

	public String getGuid() {
		return _ehcache.getGuid();
	}

	public Object getInternalContext() {
		return _ehcache.getInternalContext();
	}

	public List getKeys() throws IllegalStateException, CacheException {
		return _ehcache.getKeys();
	}

	public List getKeysNoDuplicateCheck() throws IllegalStateException {
		return _ehcache.getKeysNoDuplicateCheck();
	}

	public List getKeysWithExpiryCheck()
		throws IllegalStateException, CacheException {

		return _ehcache.getKeysWithExpiryCheck();
	}

	public LiveCacheStatistics getLiveCacheStatistics()
		throws IllegalStateException {

		return _ehcache.getLiveCacheStatistics();
	}

	public long getMemoryStoreSize() throws IllegalStateException {
		return _ehcache.getMemoryStoreSize();
	}

	public String getName() {
		return _ehcache.getName();
	}

	public Element getQuiet(Serializable serializable)
		throws IllegalStateException, CacheException {

		return _ehcache.getQuiet(serializable);
	}

	public Element getQuiet(Object o)
		throws IllegalStateException, CacheException {

		return _ehcache.getQuiet(o);
	}

	public List<CacheExtension> getRegisteredCacheExtensions() {
		return _ehcache.getRegisteredCacheExtensions();
	}

	public List<CacheLoader> getRegisteredCacheLoaders() {
		return _ehcache.getRegisteredCacheLoaders();
	}

	public CacheWriter getRegisteredCacheWriter() {
		return _ehcache.getRegisteredCacheWriter();
	}

	public SampledCacheStatistics getSampledCacheStatistics() {
		return _ehcache.getSampledCacheStatistics();
	}

	public int getSize() throws IllegalStateException, CacheException {
		return _ehcache.getSize();
	}

	public int getSizeBasedOnAccuracy(int i)
		throws IllegalArgumentException, IllegalStateException, CacheException {
		return _ehcache.getSizeBasedOnAccuracy(i);
	}

	public Statistics getStatistics() throws IllegalStateException {
		return _ehcache.getStatistics();
	}

	public int getStatisticsAccuracy() {
		return _ehcache.getStatisticsAccuracy();
	}

	public Status getStatus() {
		return _ehcache.getStatus();
	}

	public Ehcache getWrappedCache() {
		return _ehcache;
	}

	public Element getWithLoader(Object o, CacheLoader cacheLoader, Object o1)
		throws CacheException {

		return _ehcache.getWithLoader(o, cacheLoader, o1);
	}

	public CacheWriterManager getWriterManager() {
		return _ehcache.getWriterManager();
	}

	public int hashCode() {
		return _ehcache.hashCode();
	}

	public void initialise() {
		_ehcache.initialise();
	}

	public boolean isClusterCoherent() {
		return _ehcache.isClusterCoherent();
	}

	public boolean isDisabled() {
		return _ehcache.isDisabled();
	}

	public boolean isElementInMemory(Object o) {
		return _ehcache.isElementInMemory(o);
	}

	public boolean isElementInMemory(Serializable serializable) {
		return _ehcache.isElementInMemory(serializable);
	}

	public boolean isElementOnDisk(Object o) {
		return _ehcache.isElementOnDisk(o);
	}

	public boolean isElementOnDisk(Serializable serializable) {
		return _ehcache.isElementOnDisk(serializable);
	}

	public boolean isExpired(Element element)
		throws IllegalStateException, NullPointerException {

		return _ehcache.isExpired(element);
	}

	public boolean isKeyInCache(Object o) {
		return _ehcache.isKeyInCache(o);
	}

	public boolean isNodeCoherent() {
		return _ehcache.isNodeCoherent();
	}

	public boolean isSampledStatisticsEnabled() {
		return _ehcache.isSampledStatisticsEnabled();
	}

	public boolean isStatisticsEnabled() {
		return _ehcache.isStatisticsEnabled();
	}

	public boolean isValueInCache(Object o) {
		return _ehcache.isValueInCache(o);
	}

	public void load(Object o) throws CacheException {
		_ehcache.load(o);
	}

	public void loadAll(Collection collection, Object o) throws CacheException {
		_ehcache.loadAll(collection, o);
	}

	public void put(Element element)
		throws IllegalArgumentException, IllegalStateException, CacheException {

		_ehcache.put(element);
	}

	public void put(Element element, boolean b)
		throws IllegalArgumentException, IllegalStateException, CacheException {

		_ehcache.put(element, b);
	}

	public Element putIfAbsent(Element element) throws NullPointerException {

		return _ehcache.putIfAbsent(element);
	}

	public void putQuiet(Element element)
		throws IllegalArgumentException, IllegalStateException, CacheException {

		_ehcache.putQuiet(element);
	}

	public void putWithWriter(Element element)
		throws IllegalArgumentException, IllegalStateException, CacheException {

		_ehcache.putWithWriter(element);
	}

	public void registerCacheExtension(CacheExtension cacheExtension) {
		_ehcache.registerCacheExtension(cacheExtension);
	}

	public void registerCacheLoader(CacheLoader cacheLoader) {
		_ehcache.registerCacheLoader(cacheLoader);
	}

	public void registerCacheUsageListener(
			CacheUsageListener cacheUsageListener)
		throws IllegalStateException {

		_ehcache.registerCacheUsageListener(cacheUsageListener);
	}

	public void registerCacheWriter(CacheWriter cacheWriter) {
		_ehcache.registerCacheWriter(cacheWriter);
	}

	public boolean remove(Object key) throws IllegalStateException {
		return _ehcache.remove(key);
	}

	public boolean remove(Serializable key)
		throws IllegalStateException {

		return _ehcache.remove(key);
	}

	public boolean remove(Object key, boolean doNotNotifyCacheReplicators)
		throws IllegalStateException {

		return _ehcache.remove(key, doNotNotifyCacheReplicators);
	}

	public boolean remove(Serializable key, boolean doNotNotifyCacheReplicators)
		throws IllegalStateException {

		return _ehcache.remove(key, doNotNotifyCacheReplicators);
	}

	public void removeAll() throws IllegalStateException, CacheException {
		_ehcache.removeAll();
	}

	public void removeAll(boolean doNotNotifyCacheReplicators)
		throws IllegalStateException, CacheException {

		_ehcache.removeAll(doNotNotifyCacheReplicators);
	}

	public void removeCacheUsageListener(CacheUsageListener cacheUsageListener)
		throws IllegalStateException {

		_ehcache.removeCacheUsageListener(cacheUsageListener);
	}

	public boolean removeElement(Element element) throws NullPointerException {
		return _ehcache.removeElement(element);
	}

	public void removePropertyChangeListener(
		PropertyChangeListener propertyChangeListener) {

		_ehcache.removePropertyChangeListener(propertyChangeListener);
	}

	public boolean removeQuiet(Object key) throws IllegalStateException {
		return _ehcache.removeQuiet(key);
	}

	public boolean removeQuiet(Serializable serializable)
		throws IllegalStateException {

		return _ehcache.removeQuiet(serializable);
	}

	public void removeReference() {
		_referenceCounter.decrementAndGet();
	}

	public boolean removeWithWriter(Object key)
		throws IllegalStateException, CacheException {

		return _ehcache.removeWithWriter(key);
	}

	public Element replace(Element element) throws NullPointerException {
		return _ehcache.replace(element);
	}

	public boolean replace(Element oldElement, Element newElement)
		throws NullPointerException, IllegalArgumentException {

		return _ehcache.replace(oldElement, newElement);
	}

	public void setBootstrapCacheLoader(
			BootstrapCacheLoader bootstrapCacheLoader)
		throws CacheException {

		_ehcache.setBootstrapCacheLoader(bootstrapCacheLoader);
	}

	public void setCacheExceptionHandler(
		CacheExceptionHandler cacheExceptionHandler) {

		_ehcache.setCacheExceptionHandler(cacheExceptionHandler);
	}

	public void setCacheManager(CacheManager cacheManager) {
		_ehcache.setCacheManager(cacheManager);
	}

	public void setDisabled(boolean disabled) {
		_ehcache.setDisabled(disabled);
	}

	public void setDiskStorePath(String diskStorePath) throws CacheException {
		_ehcache.setDiskStorePath(diskStorePath);
	}

	public void setName(String name) {
		_ehcache.setName(name);
	}

	public void setNodeCoherent(boolean nodeCoherent)
		throws UnsupportedOperationException {

		_ehcache.setNodeCoherent(nodeCoherent);
	}

	public void setSampledStatisticsEnabled(boolean sampleStatisticsEnabled) {
		_ehcache.setSampledStatisticsEnabled(sampleStatisticsEnabled);
	}

	public void setStatisticsAccuracy(int statisticsAccuracy) {
		_ehcache.setStatisticsAccuracy(statisticsAccuracy);
	}

	public void setStatisticsEnabled(boolean statisticsEnabled) {
		_ehcache.setStatisticsEnabled(statisticsEnabled);
	}

	public void setTransactionManagerLookup(
		TransactionManagerLookup transactionManagerLookup) {

		_ehcache.setTransactionManagerLookup(transactionManagerLookup);
	}

	public void setWrappedCache(Ehcache ehcache) {
		_ehcache = ehcache;
	}

	public void unregisterCacheExtension(CacheExtension cacheExtension) {
		_ehcache.unregisterCacheExtension(cacheExtension);
	}

	public void unregisterCacheLoader(CacheLoader cacheLoader) {
		_ehcache.unregisterCacheLoader(cacheLoader);
	}

	public void unregisterCacheWriter() {
		_ehcache.unregisterCacheWriter();
	}

	public void waitUntilClusterCoherent()
		throws UnsupportedOperationException {

		_ehcache.waitUntilClusterCoherent();
	}

	private Ehcache _ehcache;
	private AtomicInteger _referenceCounter = new AtomicInteger(0);
}