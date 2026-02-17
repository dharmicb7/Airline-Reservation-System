const API_URL = "http://localhost:8080/api";

const businessContainer = document.getElementById("businessSeats");
const economyContainer = document.getElementById("economySeats");

const selectedCountEl = document.getElementById("selectedCount");
const totalPriceEl = document.getElementById("totalPrice");
const availableCountEl = document.getElementById("availableCount");

const resetBtn = document.getElementById("resetBtn");
const bookBtn = document.getElementById("bookBtn");

const BUSINESS_PRICE = 5000;
const ECONOMY_PRICE = 2000;

let selectedSeats = new Set();
let availableSeatsFromBackend = new Set();

function createSeat(seatId, price) {
  const seat = document.createElement("div");
  seat.classList.add("seat");
  seat.textContent = seatId;
  seat.dataset.price = price;
  seat.dataset.id = seatId;

  if (availableSeatsFromBackend.has(seatId)) {
    seat.classList.add("available");
  } else {
    seat.classList.add("booked");
  }

  return seat;
}

async function loadSeatsFromBackend() {
  const res = await fetch(`${API_URL}/seats`);
  const data = await res.json();

  availableSeatsFromBackend = new Set(data);

  renderSeats();
  updateAvailableCount();
}

function renderSeats() {
  businessContainer.innerHTML = "";
  economyContainer.innerHTML = "";

  // Business Class (1–3)
  for (let row = 1; row <= 3; row++) {
    ["A", "B", "", "C", "D"].forEach(letter => {
      if (letter === "") {
        businessContainer.appendChild(document.createElement("div"));
      } else {
        const seat = createSeat(row + letter, BUSINESS_PRICE);
        businessContainer.appendChild(seat);
      }
    });
  }

  // Economy Class (4–15)
  for (let row = 4; row <= 15; row++) {
    ["A","B","C","","D","E","F"].forEach(letter => {
      if (letter === "") {
        economyContainer.appendChild(document.createElement("div"));
      } else {
        const seat = createSeat(row + letter, ECONOMY_PRICE);
        economyContainer.appendChild(seat);
      }
    });
  }
}

function updateAvailableCount() {
  availableCountEl.textContent = availableSeatsFromBackend.size;
}

function updateSummary() {
  selectedCountEl.textContent = selectedSeats.size;

  let total = 0;
  selectedSeats.forEach(id => {
    const seat = document.querySelector(`[data-id='${id}']`);
    if (seat) {
      total += Number(seat.dataset.price);
    }
  });

  totalPriceEl.textContent = total;
}

document.addEventListener("click", function(e) {

  if (!e.target.classList.contains("seat")) return;
  if (e.target.classList.contains("booked")) return;

  const seat = e.target;
  const id = seat.dataset.id;

  if (seat.classList.contains("selected")) {
    seat.classList.remove("selected");
    selectedSeats.delete(id);
  } else {
    seat.classList.add("selected");
    selectedSeats.add(id);
  }

  updateSummary();
});

resetBtn.addEventListener("click", () => {
  selectedSeats.forEach(id => {
    const seat = document.querySelector(`[data-id='${id}']`);
    if (seat) seat.classList.remove("selected");
  });

  selectedSeats.clear();
  updateSummary();
});

bookBtn.addEventListener("click", async () => {

  if (selectedSeats.size === 0) {
    alert("No seats selected!");
    return;
  }

  const response = await fetch(`${API_URL}/book`, {   // ✅ FIXED HERE
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(Array.from(selectedSeats))
  });

  const message = await response.text();
  alert(message);

  selectedSeats.clear();
  updateSummary();

  await loadSeatsFromBackend(); 
});

loadSeatsFromBackend();
