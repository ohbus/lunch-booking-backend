#
# Lunch Booking - Lunch Booking REST Application
# Copyright © 2022 Subhrodip Mohanta (hello@subho.xyz)
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#

FROM amazoncorretto:17

LABEL maintainer="Subhrodip Mohanta <hello@subho.xyz>"
LABEL artifact="lunch-booking-backend"
LABEL platform="java"
LABEL name="Lunch Booking REST Application"
LABEL org.opencontainers.image.source="https://github.com/ohbus/lunch-booking-backend"

# If the container is launched with re-mapped ports, these 
# ENV vars should be set to the remapped values.

ENV ALLOWED_DOMAINS irdeto.com

ENV CORS_URL https://lunch.subho.xyz

ENV EMAIL_FROM_ADDR hello@subho.xyz

ENV EMAIL_MANAGERS_TO_ADDR hello+manager@subho.xyz
ENV EMAIL_MANAGER_CC_ADDR hello+manager+cc@subho.xyz

ENV EMAIL_REPLY_TO_ADDR hello@subho.xyz
ENV EMAIL_REPLY_TO_NAME Subhrodip Mohanta

ENV KEYSTORE_PASSWORD Subh0@123
ENV KEYSTORE_ALIAS subhrodip
ENV KEYSTORE_PASSPHRASE Subh0@123

ENV MYSQL_DB_HOST db
ENV MYSQL_DB_PORT 3306
ENV MYSQL_DB_UNAME root
ENV MYSQL_DB_PASSWD root

ENV SMTP_UNAME username
ENV SMTP_PASSWD password
ENV SMTP_URL smtp.gmail.com
ENV SMTP_PORT 587


ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# If you are changing server port, be sure to change this as well
EXPOSE 8080

# Running the application with `prod` profile
ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=prod", "/app.jar" ]
