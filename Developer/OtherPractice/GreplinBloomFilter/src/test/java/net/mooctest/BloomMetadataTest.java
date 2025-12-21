package net.mooctest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BloomMetadataTest {

	@Test
	public void testCreateNew() throws IOException {
		RepeatedMurmurHash repeatedMurmurHash = new RepeatedMurmurHash(5, 10);
		int[] arr = repeatedMurmurHash.hash(new byte[] { 'a', 'b', 'c', 2 });
		int[] a = new int[] { 3, 1, 5, 7, 3 };
		assertArrayEquals(a, arr);

	}

	@Test
	public void testCreateNew1() throws IOException {
		BloomMetadata metadata = BloomMetadata.createNew(10, 2, BucketSize.ONE);
		assertEquals(BloomMetadata.VERSION, metadata.version);
		assertEquals(BloomMetadata.EXPECTED_HEADER_BYTES, metadata.headerLength);
		assertEquals(BloomMetadata.EXPECTED_HEADER_BYTES + 2, metadata.totalLength);
		assertEquals(2, metadata.hashFns);
		assertEquals(BucketSize.ONE, metadata.bucketSize);

		RepeatedMurmurHash repeatedMurmurHash = new RepeatedMurmurHash(5, 10);
		int[] arr = repeatedMurmurHash.hash(new byte[] { 'a', 'b', 'c', 2 });
		int[] a = new int[] { 3, 1, 5, 7, 3 };
		assertArrayEquals(a, arr);

	}
}
