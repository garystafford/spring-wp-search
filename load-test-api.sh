#!/bin/sh

for i in 1...25
do
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search?minScore=1&size=3&start=0&value=Istio
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search?minScore=1&size=6&start=0&value=Docker
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search?minScore=1&size=9&start=0&value=Kubernetes
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search?minScore=.5&size=6&start=0&value=GCP
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search?minScore=.5&size=6&start=0&value=AWS
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/22454
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/22313
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/22141
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search/hits?minScore=0.75&value=Spring
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/dismax-search/hits?minScore=1.25&value=Mongo
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/simple-search?field=post_title&value=Docker
  sleep 2
  http http://api.chatbotzlabs.com/blog/api/v1/elastic/simple-search?field=post_title&value=Cosmos
done
