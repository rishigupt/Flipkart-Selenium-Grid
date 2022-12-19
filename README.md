# Selenium-Grid
Browser Automation by spinning selenium grid infrastructure using docker compose file.
docker-compose up will exceute before suite, hence starting the Hub and Nodes.
docker-compose down will execure after suite, hence stopping the grid infrastructure down.
The prerequisites to excute this testcases are:
Install Docker.
Install Maven.
Once the installation is done, execute below command on the project path:
mvn clean test


