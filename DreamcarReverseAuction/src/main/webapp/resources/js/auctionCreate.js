function checkMinEndDate() {
	var today = new Date();
	var dd = parseInt(today.getDate()) + 1;
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	tomorrow = mm + '-' + dd + '-' + yyyy;
	document.getElementById("endDate").setAttribute("min", tomorrow);
};

function togglePanelCreate(el) {
	var auctionCreatePanel = document
			.getElementById("toggle-panel-auction-create-Id");
	var componentCreatePanel = document
			.getElementById("toggle-panel-component-create-Id");
	var iconAuctionBtn = document.getElementById("new-auction-arrow-toggle-id");
	var iconComponentBtn = document
			.getElementById("new-component-arrow-toggle-id");

	var selectedPanel = el.id === "new-auction-btn-id" ? auctionCreatePanel
			: componentCreatePanel;
	var selectedIcon = selectedPanel === auctionCreatePanel ? iconAuctionBtn
			: iconComponentBtn;

	if (selectedPanel.classList.contains("create-panel-close")) {
		selectedPanel.classList.remove("create-panel-close");
		selectedPanel.classList.add("create-panel-open");

		if (selectedPanel === componentCreatePanel) {
			selectedIcon.classList.add("fa-angle-left");
			selectedIcon.classList.remove("fa-angle-right");
		} else {
			selectedIcon.classList.add("fa-angle-up");
			selectedIcon.classList.remove("fa-angle-down");
		}
	} else {
		selectedPanel.classList.add("create-panel-close");
		selectedPanel.classList.remove("create-panel-open");
		componentCreatePanel.classList.add("create-panel-close");
		componentCreatePanel.classList.remove("create-panel-open");
		iconComponentBtn.classList.add("fa-angle-right");
		iconComponentBtn.classList.remove("fa-angle-left");
		
		if (selectedPanel === iconAuctionBtn) {
			selectedIcon.classList.add("fa-angle-down");
			selectedIcon.classList.remove("fa-angle-up");
		}
	}
}