@REM ----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM ----------------------------------------------------------------------------

@if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@setlocal
@set ERROR_CODE=0

@set MAVEN_PROJECTBASEDIR=%~dp0
@if "%MAVEN_PROJECTBASEDIR%" == "" set MAVEN_PROJECTBASEDIR=%CD%

@set MAVEN_CONFIG=%MAVEN_PROJECTBASEDIR%\.mvn

@set MAVEN_CMD_LINE_ARGS=%*

@call "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.cmd" %MAVEN_CMD_LINE_ARGS%
@if %ERRORLEVEL% NEQ 0 goto error
@goto end

:error
@set ERROR_CODE=1

:end
@endlocal & set ERROR_CODE=%ERROR_CODE%
