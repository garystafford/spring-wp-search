# Spring-Wordpress-Elasticseach Example

## References

```bash
java -jar -Dspring.profiles.active=gcp search-1.0.1.jar
docker stack deploy -c stack.yml search
docker logs search_elastic_1 --follow
DELETE localhost8681-1/post/2

```
- <https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#repositories>
- <https://www.baeldung.com/spring-data-elasticsearch-queries>
- <https://www.baeldung.com/spring-rest-template-list>
- <https://www.baeldung.com/jackson-nested-values>
- <https://www.baeldung.com/elasticsearch-java>
- <https://cloud.google.com/appengine/docs/flexible/java/using-gradle#creating_a_new_project>
- <https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2>
