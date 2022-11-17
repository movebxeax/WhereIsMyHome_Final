async function findDealData() {
	let sidoCode = document.querySelector("#sido").value;
	let gugunCode = document.querySelector("#gugun").value;
	let dongCode = document.querySelector("#dong").value;
	let dealYear = document.querySelector("#year").value;
	let aptName = document.querySelector("#name").value;
	let interest = document.querySelector("#interest").checked;
	let commercial = document.querySelector("#commercial").checked;
	let innersearch = document.querySelector("#innersearch").checked;
  
	if(innersearch)
	{
		innerSearch();
		return;
	}
	
	removeMarker();
	eraseDealElementTable();

	if (dealYear == undefined) dealYear = 0;

	let lat1 = -1;
	let lng1 = -1;

	let res = await fetch(getContextPath() + "/api/trade?" + new URLSearchParams({
		dongCode,
		dealYear,
		aptName,
		}))
		.then((response) => response.json())
		.then((datas) => {
			for (let data of datas) {
				let myLatLng = new kakao.maps.LatLng(data.lat, data.lng);
				displayMarker(myLatLng, data.aptName, -1);
				if (lat1 == -1 || lng1 == -1) {
					lat1 = data.lat;
					lng1 = data.lng;
				}
				addDealElementToTable(data);
			}
		})
		.catch((error) => { });

	if (interest) {
		console.log("test");
		let res = await fetch(getContextPath() + "/api/interest",
			{
				method: "POST",
				cache: "no-cache",
				body: {
					sidoCode,
					gugunCode,
					dongCode,
				},
			}
		).then((response) => {
			if (!response.ok) alert("관심지역 설정에 실패했습니다.");
		});
	}

	if (commercial) {
		let res2 = fetch(getContextPath() + "/api/commercial/"+dongCode)
			.then((response) => response.json())
			.then((datas) => {
				for (let data of datas) {
					let meters = measure(lat1, lng1, data.lat, data.lng);
					if (meters <= document.querySelector("#distance").value) {
						let myLatLng = new kakao.maps.LatLng(data.lat, data.lng);
						let imgSrc = getContextPath() + "/img/" + data.categoryCode + ".PNG";
						displayMarker(myLatLng, data.name, imgSrc);
					}
				}
			})
			.catch((error) => { console.log(error); });

	}
}

function eraseDealElementTable() {
	let dealTableBody = document.querySelector("#dealTableBody");
	let rows = dealTableBody.rows.length;
	for (let row = 0; row < rows; row++) {
		dealTableBody.rows[0].remove();
	}
}

function addDealElementToTable(data) {
	let dealTableBody = document.querySelector("#dealTableBody");
	let tr = document.createElement("tr");
	let td = document.createElement("td");
	let aptName = document.createElement("h5");
	aptName.innerText = data["aptName"];
	aptName.setAttribute("id", "dealTableAptName");
	let address = document.createElement("h6");

	address.innerText =
		"주소 :" +
		data["sidoName"] +
		" " +
		data["gugunName"] +
		" " +
		data["dongName"] +
		" " +
		data["jibun"] +
		" " +
		data["floor"] +
		"층";
	let dealYearMonth = document.createElement("h6");
	dealYearMonth.innerText =
		"거래일 : " + data["dealYear"] + "년 " + data["dealMonth"] + "월";
	let dealAmount = document.createElement("h6");
	dealAmount.innerText = "거래금액 : " + data["dealAmount"] + " 원";

	td.appendChild(aptName);
	td.appendChild(address);
	td.appendChild(dealYearMonth);
	td.appendChild(dealAmount);

	dealTableBody.appendChild(tr).appendChild(td);
}

function measure(lat1, lng1, lat2, lng2) {  // generally used geo measurement function
	var R = 6378.137; // Radius of earth in KM
	var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
	var dLng = lng2 * Math.PI / 180 - lng1 * Math.PI / 180;
	var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
		Math.sin(dLng / 2) * Math.sin(dLng / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	var d = R * c;
	return d * 1000; // meters
}

function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(
		hostIndex,
		location.href.indexOf("/", hostIndex + 1)
	);
	return contextPath;
}

function setInterestInfoIfExist() {
	let gugunCode = document.querySelector("#gugun").value;
	let dongCode = document.querySelector("#dong").value;

	let res = fetch(getContextPath() + "/api/interest")
		.then((response) => response.json())
		.then((datas) => {
			let key = Object.keys(datas);
			let key_count = Object.keys(datas).length;

			for (let i = 0; i < key_count; i++) {
				if (key[i] == "sido") {
					getListAndSetInterestValue(
						key[i],
						`${getContextPath()}/api/address/sido`,
						null,
						datas[key[i]]
					);
				} else if (key[i] == "gugun") {
					let regcode = datas[key[i]].substr(0, 2);
					getListAndSetInterestValue(
						key[i],
						`${getContextPath()}/api/address/gugun/`+regcode,
						regcode,
						datas[key[i]]
					);
				} else if (key[i] == "dong") {
					let regcode = datas[key[i]].substr(0, 5);
					getListAndSetInterestValue(
						key[i],
						`${getContextPath()}/api/address/dong/`+regcode,
						regcode,
						datas[key[i]]
					);
				}
			}
		})
		.catch((error) => { });
}

function innerSearch()
{
	let name = document.querySelector("#name").value;
	let datas = document.querySelectorAll("#dealTableAptName");
	datas.forEach(data => {
    if(!data.innerText.includes(name)){
        let tr = data.parentNode.parentNode;
        tr.parentNode.removeChild(tr);
        removeDesignatedMarker(data.innerText);
    }
});
}
