# jenkins-testing-demo-platform
A quick way to make a minimal, repeatable Jenkins instance that demonstrates an issue


## Usage

This is a simplistic Jenkins startup with no security, loading of plugins and loading of test job(s)
using `docker-compose`


### Prereqs

Working installation of Docker and docker-compose


### Configure

1. Configure plugin list in [startup/plugins.txt]()

2. Configure startup config in [startup/jcasc.yaml]()

3. Configure seed job in JobDSL format in  [startup/jobs.groovy]()


### Home directory

By default, home directory and all the configuration is *_not_* kept between 
runs
If you want to keep in, uncomment the appropriate line in [docker-compose.
yaml]()

    volumes:
      # uncomment to keep jenkins home between restarts
      #- ./data:/var/jenkins_home
      - ./startup:/startup

### Running

Simple `docker-compose up` and `docker-compose down` commands will do

Since I ofter run this repeatedly, I often do things like:

    docker-compose up; docker-compose down

or even:

    while true; do docker-compose up; docker-compose down; done

This way the test setup runs in foreground  until you hit CTRL-C and then 
cleans up.
