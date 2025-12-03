@echo off
chcp 65001 > nul
echo Iniciando Sistema de Gestion de Biblioteca...
echo.
gradlew.bat run --console=plain
pause
