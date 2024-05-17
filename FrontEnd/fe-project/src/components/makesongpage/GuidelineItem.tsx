interface GuideItemProps {
    guide: guideItem;
}

export default function GuidelineItem({guide}: GuideItemProps ){
    return (
        <div className="flex flex-col justify-center mb-3">
            <div className="font-['Pretendard'] font-extrabold">
                [{guide.name}]
            </div>
            <div className="font-['Pretendard'] font-medium">
                {guide.description}
            </div>
        </div>
    );
}