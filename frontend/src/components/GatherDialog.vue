<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Summon gather" : "Join gather" }}
        </v-toolbar>
        <v-form>
          <v-text-field
            v-model="gather.title"
            label="Title"
            :disabled="!isNew"
          />
          <v-text-field
            v-model="gather.subject"
            label="Subject"
            :disabled="!isNew"
          />
        </v-form>

        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Join" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "GatherDialog",
  props: {
    gather: Object,
    userId: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.gathers
          .create({
            id: this.userId,
            title: this.gather.title,
            subject: this.gather.subject,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.gathers
          .joinGather({
            gatherId: this.gather.id,
            userId: this.userId,
          })
          .then(() => this.$emit("refresh"));
        api.gathers.sendEmail(this.userId);

        api.sms
          .sendSMS({
            userId: this.userId,
          })
          .then(() => this.$emit("refresh"));
        api.gathers.sendEmail(this.userId);
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.gather.id;
    },
  },
};
</script>

<style scoped></style>
