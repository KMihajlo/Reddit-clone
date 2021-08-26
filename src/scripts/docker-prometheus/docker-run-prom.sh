# just to be used the first time
# "--name" choose a name for your container
# "port:port" choose a port to place grafana container

docker run -p 9090:9090 -v C:\Users\mkraguje\IdeaProjects\reddit-clone\src\main\resources\prometheus.yml prom/prometheus