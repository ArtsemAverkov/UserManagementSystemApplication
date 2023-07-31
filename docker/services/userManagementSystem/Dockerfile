FROM openjdk:17-jdk
WORKDIR /usr/local/UserManagementSystem
ADD UserManagementSystem-0.0.6.jar UserManagementSystem.jar
CMD java -jar ./UserManagementSystem.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
CMD ["./wait-for-it.sh", "mySql:3306", "--", "java", "-jar", "UserManagementSystem.jar"]