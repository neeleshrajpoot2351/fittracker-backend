@echo off
echo ========================================
echo Starting FitTracker Backend...
echo ========================================
echo.

rem Skip tests for faster startup
call mvnw.cmd spring-boot:run -DskipTests

echo.
echo ========================================
echo Backend stopped or failed to start
echo ========================================
echo.
pause
