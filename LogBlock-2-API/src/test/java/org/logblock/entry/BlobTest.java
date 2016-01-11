package org.logblock.entry;

import org.junit.Assert;
import org.junit.Test;
import org.logblock.entry.blob.PaintingBlob;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class BlobTest
{

    @Test
    public void paintingTest() throws Exception
    {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        DataOutputStream outStream = new DataOutputStream(byteOutput);

        PaintingBlob blobOut = BlobEntry.create(1, PaintingBlob.class);
        blobOut.setArt("artistic");
        blobOut.setDirection((byte) 5);
        blobOut.write(outStream);
        outStream.close();

        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(byteOutput.toByteArray()));
        PaintingBlob blobIn = BlobEntry.create(1, PaintingBlob.class);
        blobIn.read(inputStream);

        Assert.assertEquals(blobOut, blobIn);
    }
}
