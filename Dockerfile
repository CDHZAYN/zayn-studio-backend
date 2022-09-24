FROM node:16

MAINTAINER cdhzayn

EXPOSE 8080

RUN mkdir -p /opt/studio-backend

WORKDIR /opt/studio-backend

COPY . /opt/studio-backend

ENTRYPOINT ["npm", "run", "prod"]