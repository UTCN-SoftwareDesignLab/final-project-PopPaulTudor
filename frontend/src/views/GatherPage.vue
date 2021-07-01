<template>
  <v-card>
    <v-card-title>
      Group Score: {{ this.localPoints }} All Score: {{ this.totalPoints }}
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="myGathers">My Groups</v-btn>
      <v-btn @click="addAnnouncement">Make Announcement</v-btn>
      <v-btn @click="downloadInfo">Download</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="announcements"
      :search="search"
      @click:row="editGather"
    ></v-data-table>

    <div class="large-12 medium-12 small-12 cell">
      <label
        >File
        <input
          type="file"
          id="file"
          ref="file"
          v-on:change="handleFileUpload()"
        />
      </label>
      <v-btn v-on:click="submitFile()">Submit</v-btn>
    </div>
    <AnnouncementDialog
      :opened="dialogVisible"
      :announcement="selectedAnnouncement"
      :gatherId="this.$route.params.gatherId"
      :userId="this.$store.state.auth.user.id"
      @refresh="refreshList"
    ></AnnouncementDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "../router";
import AnnouncementDialog from "../components/AnnouncementDialog";
export default {
  name: "GatherPage",
  components: { AnnouncementDialog },
  data() {
    return {
      announcements: [],
      totalPoints: 0,
      localPoints: 0,
      file: "",
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "Subject", value: "subject" },
        { text: "Content", value: "content" },
      ],
      dialogVisible: false,
      selectedAnnouncement: {},
    };
  },
  methods: {
    addAnnouncement() {
      this.dialogVisible = true;
    },
    submitFile() {
      let formData = new FormData();
      formData.append("file", this.file);
      api.gathers.upload(this.file);
    },
    downloadInfo(){
      api.gathers.download();
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },
    myGathers() {
      router.push("/myGathers");
    },
    async refreshList() {
      this.announcements = await api.announcements.allAnnouncementsFromGatherID(
        this.$route.params.gatherId
      );
      this.totalPoints = await api.pairs.allPoints(
        this.$store.state.auth.user.id
      );
      this.localPoints = await api.pairs.allPointsFromGroup(
        this.$store.state.auth.user.id,
        this.$route.params.gatherId
      );
    },
  },
  created() {
    this.refreshList();
  },
  mounted() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
