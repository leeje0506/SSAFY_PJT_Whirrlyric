import AlbumCard from "../components/mainpage/AlbumCard";
import DetailPlayListCard from "../components/mainpage/DetailPlayListCard";

export default function Main() {
  const cards = Array.from({ length: 5 }, (_, index) => index);

  return (
    <>
      <div>
        <h1>인기곡</h1>
        <div>
          {cards.map((card) => (
            <DetailPlayListCard key={card} />
          ))}
        </div>
      </div>
      <div className="mt-8 w-[400px] overflow-auto no-scroll">
        <h1>최신 음악</h1>
        <div className="flex">
          {cards.map((card) => (
            <AlbumCard key={card} />
          ))}
        </div>
      </div>
      <div className="mt-8 w-[400px] overflow-auto no-scroll">
        <h1>POP</h1>
        <div className="flex">
          {cards.map((card) => (
            <AlbumCard key={card} />
          ))}
        </div>
      </div>
    </>
  );
}
