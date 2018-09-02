package com.example.elasticsearch.utils;

import com.example.elasticsearch.model.WordpressPost;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.IOException;

public class WordpressPostDeserializer extends StdDeserializer<WordpressPost> {

    public WordpressPostDeserializer() {

        this(null);
    }

    public WordpressPostDeserializer(Class<?> vc) {

        super(vc);
    }

    public WordpressPost deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode PostWordpressNode = jp.getCodec().readTree(jp);
        WordpressPost wordpressPost = new WordpressPost();
        wordpressPost.setId(PostWordpressNode.get("id").longValue());
        wordpressPost.setDateGMT(PostWordpressNode.get("date_gmt").textValue());
        wordpressPost.setGuid(PostWordpressNode.get("guid").get("rendered").textValue());
        wordpressPost.setTitle(PostWordpressNode.get("title").get("rendered").textValue());
        String excerpt = PostWordpressNode.get("excerpt").get("rendered").textValue();
        excerpt = Jsoup.clean(excerpt, Whitelist.none())
                .replaceAll("&nbsp;", " ").trim();
        wordpressPost.setExcerpt(excerpt);
        return wordpressPost;
    }

}
