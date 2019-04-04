#FROM node:8
#WORKDIR /usr/src/app
#COPY app/package*.json ./
#RUN npm install
#COPY app/ .
#EXPOSE 8000
#RUN echo $(ls -1 .)
#CMD ["npm", "start"]



FROM ubuntu:latest
WORKDIR /usr/src/app
COPY app/package*.json ./
RUN apt-get update
RUN apt-get install -y nodejs
RUN apt-get install -y npm
RUN npm -g install http-server
RUN npm install
COPY app/ .
EXPOSE 8000
RUN echo $(ls -1 .)
CMD [ "npm", "start" ]
#CMD ['http-server', '-p', '8000', '.']


#FROM fedora
#WORKDIR /usr/src/app
#COPY app/package*.json ./
#RUN dnf install -y npm
#RUN npm install
#COPY app/ .
#EXPOSE 8000
#RUN echo $(ls -1 .)
#CMD [ "npm", "start" ]



#FROM node:8
# Create app directory
#WORKDIR /usr/src/app
# Install app dependencies
#COPY app/package*.json ./
#RUN npm install
# Bundle app source
#COPY . .
#EXPOSE 8000
#CMD [ "npm", "start" ]
