$(document).ready(function() {
  // Select element change event
  $("#trackinglist").change(() => {
      var imageValue = $(this).val();
      var imageSrc = "";

      if (imageValue === "1") {
          imageSrc = "static/images/tracking1.png";
      } else if (imageValue === "2") {
          imageSrc = "/src/main/resources/static/images/tracking2.png";
      } else if (imageValue === "3") {
          imageSrc = "/src/main/resources/static/images/tracking3.png";
      }

      $("#trackingImage").attr("src", imageSrc);
  });
});