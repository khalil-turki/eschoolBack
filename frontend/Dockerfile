# Use an official Node runtime as a base image
FROM node:14

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install app dependencies
RUN npm install --omit=dev

# Copy the application files to the working directory
COPY . .

# Expose the port the app runs on
EXPOSE 4200

# Define the command to run your app
CMD ["npm", "start", "--host", "0.0.0.0"]
