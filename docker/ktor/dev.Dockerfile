FROM gradle:8.8
COPY --chown=gradle:gradle build.gradle.* settings.gradle.* gradle.properties /home/gradle/app/
COPY ./gradle /home/gradle/app/gradle
COPY bash/develop/start.sh /home/gradle/app/

COPY ./src /home/gradle/app/src/
WORKDIR /home/gradle/app

CMD bash start.sh