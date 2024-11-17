FROM gradle:8.8
COPY --chown=gradle:gradle build.gradle.* settings.gradle.* gradle.properties /home/gradle/app/
COPY ./gradle /home/gradle/app/gradle
COPY bash/develop/start.sh /home/gradle/app/

COPY ./detekt /home/gradle/app/detekt/
COPY ./infrastructure /home/gradle/app/infrastructure/
COPY ./domain /home/gradle/app/domain/
COPY ./adapter /home/gradle/app/adapter/

WORKDIR /home/gradle/app

CMD bash start.sh