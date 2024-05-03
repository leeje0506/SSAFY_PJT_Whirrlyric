import AlbumCard from "./AlbumCard";

export default function AlbumCardList() {
  const cards = Array.from({ length: 5 }, (_, index) => index);

  return (
    <div className="mt-8 w-[400px] overflow-auto no-scroll">
      <h1>최신 음악</h1>
      <div className="flex">
        {cards.map((card) => (
          <AlbumCard key={card} />
        ))}
      </div>
    </div>
  );
}
