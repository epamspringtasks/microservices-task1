package com.training.convertor;

import com.training.model.ResourceMetadata;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.SAXException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;

public class Mp3MetadataConvertor {

    public static ResourceMetadata convert(InputStream stream) throws IOException, SAXException, TikaException {

        ResourceMetadata resourceMetadata = new ResourceMetadata();
        Metadata metadata = extractMetadataUsingParser(stream);

        String[] metadataNames = metadata.names();

        if (metadataNames.length == 0)
            throw new IIOException("Invalid Metadata ");

        for (String name : metadataNames) {
            resourceMetadata.setAlbum(metadata.get("title"));
            resourceMetadata.setArtist(metadata.get("Author"));
            resourceMetadata.setName(metadata.get("dc:title"));
            resourceMetadata.setYear(metadata.get("xmpDM:releaseDate"));
            resourceMetadata.setLength(metadata.get("xmpDM:duration"));
        }
        return resourceMetadata;


    }

    private static Metadata extractMetadataUsingParser(InputStream stream) throws IOException, SAXException, TikaException {

        Metadata metadata = new Metadata();
        Mp3Parser Mp3Parser = new Mp3Parser();
        Mp3Parser.parse(stream, new BodyContentHandler(), metadata, new ParseContext());
        return metadata;

    }

}
