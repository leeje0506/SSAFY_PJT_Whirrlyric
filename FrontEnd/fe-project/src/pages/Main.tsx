import { Carousel } from "@material-tailwind/react";
import AlbumCard from "../components/mainpage/AlbumCard";
import DetailPlayListCard from "../components/mainpage/DetailPlayListCard";
import DragScrollContainer from "../utils/DragScrollContainer";

export default function Main() {
  const cards = Array.from({ length: 5 }, (_, index) => index);
  const dummys = Array.from({ length: 3 }, (_, index) => index);

  return (
    <>
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
                <DetailPlayListCard key={card} />
              ))}
            </div>
          </div>
        ))}
      </Carousel>
      <DragScrollContainer addClass="mt-8 w-[400px]">
        <h1>최신 음악</h1>
        <div className="flex">
          {cards.map((card) => (
            <AlbumCard key={card} />
          ))}
        </div>
      </DragScrollContainer>
      <DragScrollContainer addClass="mt-8 w-[400px]">
        <h1>POP</h1>
        <div className="flex">
          {cards.map((card) => (
            <AlbumCard key={card} />
          ))}
        </div>
      </DragScrollContainer>
    </>
  );
}
