package com.acme.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import org.apache.lucene.store.Directory;
import org.hibernate.search.SearchException;
import org.hibernate.search.indexes.impl.DirectoryBasedIndexManager;
import org.hibernate.search.spi.BuildContext;
import org.hibernate.search.store.DirectoryProvider;
import org.hibernate.search.store.impl.DirectoryProviderHelper;

import com.github.mongoutils.collections.DBObjectSerializer;
import com.github.mongoutils.collections.MongoConcurrentMap;
import com.github.mongoutils.collections.SimpleFieldDBObjectSerializer;
import com.github.mongoutils.lucene.MapDirectory;
import com.github.mongoutils.lucene.MapDirectoryEntry;
import com.github.mongoutils.lucene.MapDirectoryEntrySerializer;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.sun.jndi.toolkit.url.Uri;

public class MongoLuceneDirectoryProvider implements DirectoryProvider<Directory> {

	private Directory directory;

	private String indexName;
	private Properties properties;

	@Override
	public void initialize(String directoryProviderName, Properties properties,
			BuildContext context) {

		indexName = directoryProviderName;
		this.properties = properties;
		String text = System.getenv("MONGOLAB_URI");
//		String text = "mongodb://localhost/test";
		Mongo mongo;

		try {
			MongoClientURI uri = new MongoClientURI(text);
			mongo = new MongoClient(uri);

			DB db = mongo.getDB(uri.getDatabase());
			DBCollection dbCollection = db.getCollection("collection");

			DBObjectSerializer<String> keySerializer = new SimpleFieldDBObjectSerializer<String>(
					"key");
			DBObjectSerializer<MapDirectoryEntry> valueSerializer = new MapDirectoryEntrySerializer(
					"value");
			ConcurrentMap<String, MapDirectoryEntry> store = new MongoConcurrentMap<String, MapDirectoryEntry>(
					dbCollection, keySerializer, valueSerializer);

			directory = new MapDirectory(store);
		} catch (UnknownHostException e) {
			//log.error("While attempting to initalize Mongo directory provider",e);
		} catch (MongoException e) {
			//log.error("While attempting to initalize Mongo directory provider",e);
		} catch (IOException e) {
			//log.error("While attempting to initalize Mongo directory provider",e);
		}
	}

	@Override
	public void start(DirectoryBasedIndexManager indexManager) {

		try {
			directory.setLockFactory(DirectoryProviderHelper.createLockFactory(
					null, properties));
			properties = null;
			DirectoryProviderHelper.initializeIndexIfNeeded(directory);
		} catch (IOException e) {
			throw new SearchException("Unable to initialize index: "
					+ indexName, e);
		}
	}

	@Override
	public void stop() {

		try {
			directory.close();
		} catch (IOException e) {
			//log.error("While attempting to stop directory provider", e);
		}
	}

	@Override
	public Directory getDirectory() {
		return this.directory;
	}

}
