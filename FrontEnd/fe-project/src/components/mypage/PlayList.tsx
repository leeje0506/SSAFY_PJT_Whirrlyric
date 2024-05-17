import SimplePlayListCard from "./SimplePlayListCard";

interface PlayListProps {
  songList: SongWithCreator[] | null;
}

export default function PlayList({ songList }: PlayListProps) {
  return (
    <div className="ml-8">
      <div className="my-6">PlayList</div>
      {songList ? (
        <div className="max-h-96 md:max-h-60 overflow-y-auto no-scroll">
          {songList.map((songWithCreator) => (
            <SimplePlayListCard key={songWithCreator.song.songId} songWithCreator={songWithCreator}/>
          ))}
        </div>
      ) : (
        <div>No created song.</div>
      )}
    </div>
  );
}
