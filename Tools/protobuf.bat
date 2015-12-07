@echo off
pushd %~dp0
protoc msg.proto --cpp_out=..\Classes
popd