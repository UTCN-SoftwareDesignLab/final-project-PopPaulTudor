import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allAnnouncements() {
    return HTTP.get(BASE_URL + "/announcements", {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  createAnnouncement(announcement) {
    console.log(announcement);
    return HTTP.post(BASE_URL + "/announcements", announcement, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  editAnnouncement(announcement) {
    return HTTP.patch(BASE_URL + "/announcements", announcement, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  allAnnouncementsFromGatherID(gatherId) {
    return HTTP.get(BASE_URL + "/announcements/" + `${gatherId}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
};
