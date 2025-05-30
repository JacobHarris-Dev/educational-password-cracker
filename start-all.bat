@echo off

echo Starting backend...
cd backend
start mvn spring-boot:run

timeout /t 5

echo Starting frontend...
cd ..\frontend
start npm start

pause