import { Carousel } from "@material-tailwind/react";
import PlayListCard from "../components/mainpage/DetailPlayListCard";

export default function WriteSong() {
  const cards = Array.from({ length: 5 }, (_, index) => index);
  const dummys = Array.from({ length: 3 }, (_, index) => index);

  return (
    <Carousel
      className="rounded-xl"
      placeholder={undefined}
      onPointerEnterCapture={undefined}
      onPointerLeaveCapture={undefined}
    >
      {dummys.map((dummy) => (
        <div
          key={dummy}
          className="flex flex-col items-center relative h-full w-full bg-gray-200"
        >
          <h1>인기곡1</h1>
          <div>
            {cards.map((card) => (
              <PlayListCard key={card} />
            ))}
          </div>
        </div>
      ))}
    </Carousel>
  );
}
