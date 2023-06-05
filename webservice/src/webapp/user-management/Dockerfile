FROM node:20-alpine as build
RUN mkdir /app
WORKDIR /app
COPY package.json /app
RUN npm install --legacy-peer-deps
COPY . /app
RUN npm run ng build --prod --output-path=dist

FROM nginx:1.23.4-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app/dist/user-management /usr/share/nginx/html
