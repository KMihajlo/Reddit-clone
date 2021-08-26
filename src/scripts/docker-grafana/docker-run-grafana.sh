# just to be used the first time
# "--name" choose a name for your container
# "port:port" choose a port to place grafana container

docker run -d --name=grafana -p 3000:3000 grafana/grafana