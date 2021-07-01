import authHeader, { BASE_URL, HTTP } from "../http";
import axios from "axios";
import { saveAs } from "file-saver";

export default {
  allGathersPresent(userId) {
    return HTTP.get(BASE_URL + `/gathers/not/${userId}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  joinedGatherList(userId) {
    return HTTP.get(BASE_URL + `/gathers/${userId}`, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  create(gather) {
    console.log(gather);
    return HTTP.post(BASE_URL + "/gathers", gather, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  edit(gather) {
    return HTTP.patch(BASE_URL + "/gathers", gather, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },

  joinGather(gather) {
    return HTTP.post(BASE_URL + "/gathers/join", gather, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },

  sendEmail(userId) {
    console.log(userId);
    return HTTP.post(BASE_URL + `/email`, userId, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },

  upload(formData) {
    axios
      .post(BASE_URL + "/files", formData, {
        headers: authHeader(),
      })
      .then(function () {
        console.log("SUCCESS");
      })
      .catch(function () {
        console.log("FAILURE");
      });
  },

  download() {
    return HTTP.get(BASE_URL + "/files/hours.png", {
      headers: authHeader(),
      responseType: "blob",
    }).then((response) => {
      console.log(response);
      let blob = new Blob([response.data], { type: "image/png" });
      saveAs(blob, "hours.png");
    });
  },
};
