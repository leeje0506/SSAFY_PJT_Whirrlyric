import SimplePlayListCard from "./SimplePlayListCard";

export default function PlayList() {
  const cards = Array.from({ length: 5 }, (_, index) => index);

  return (
    <div className="ml-8">
      <div className="my-6">PlayList</div>
      <div className="max-h-60 overflow-y-auto no-scroll">
        {cards.map((card) => (
          <SimplePlayListCard key={card} />
        ))}
      </div>
    </div>
  );
}
