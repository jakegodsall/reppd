FROM nginx:alpine AS prod

WORKDIR /var/www/html

ADD build/configs prod-default.conf /etc/nginx/conf.d/default.conf

VOLUME ["/var/www/html"]

FROM prod AS dev

ADD build/certs/certificate.chained.crt /etc/ssl/certificate.chained.crt
ADD build/certs/wildcard.private.key /etc/ssl/wildcard.private.key

ADD build/configs/default.conf /etc/nginx/conf.d/default.conf

RUN apk add bash