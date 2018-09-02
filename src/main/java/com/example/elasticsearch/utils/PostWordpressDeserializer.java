package com.example.elasticsearch.utils;

import com.example.elasticsearch.model.PostWordpress;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PostWordpressDeserializer extends StdDeserializer<PostWordpress> {

    public PostWordpressDeserializer() {

        this(null);
    }

    public PostWordpressDeserializer(Class<?> vc) {

        super(vc);
    }

    public PostWordpress deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode PostWordpressNode = jp.getCodec().readTree(jp);
        PostWordpress postWordpress = new PostWordpress();
        postWordpress.setId(PostWordpressNode.get("id").longValue());
        postWordpress.setDateGMT(PostWordpressNode.get("date_gmt").textValue());
        postWordpress.setGuid(PostWordpressNode.get("guid").get("rendered").textValue());
        postWordpress.setTitle(PostWordpressNode.get("title").get("rendered").textValue());
        return postWordpress;
    }

}
