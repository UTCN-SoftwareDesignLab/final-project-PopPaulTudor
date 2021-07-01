import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allPoints(userId) {
    return HTTP.get(BASE_URL + `/pairs/${userId}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },

  allPointsFromGroup(userId, gatherId) {
    return HTTP.get(BASE_URL + `/pairs/${userId}/${gatherId}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
