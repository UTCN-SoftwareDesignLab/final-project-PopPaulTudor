import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  sendSMS(userId) {
    console.log(userId);
    return HTTP.post(BASE_URL + "/urlsms", userId, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
