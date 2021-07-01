<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark> Make an Announcement </v-toolbar>
        <v-form>
          <v-text-field v-model="announcement.title" label="Title" />
          <v-text-field v-model="announcement.subject" label="Subject" />
          <v-text-field v-model="announcement.content" label="Content" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist"> Make Announcement </v-btn>
          <v-btn @click="uploadFile">Upload File</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "AnnouncementDialog",
  props: {
    announcement: Object,
    userId: Object,
    gatherId: Object,
    opened: Boolean,
  },
  methods: {
    uploadFile() {},
    persist() {
      api
        .announcements
        .createAnnouncement({
          title: this.announcement.title,
          subject: this.announcement.subject,
          content: this.announcement.content,
          expeditorId: this.userId,
          gatherId: this.gatherId,
        })
        .then(() => this.$emit("refresh"));
    },
  },
};
</script>

<style scoped></style>
